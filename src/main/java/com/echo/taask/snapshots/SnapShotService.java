package com.echo.taask.snapshots;

import com.echo.taask.snapshots.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SnapShotService {
    private final MongoOperations mongoOperations;

    public ResponseEntity<?> snapShots(String authenticatedUser) {
        SnapShots snapShots = new SnapShots();
        Aggregation categoryAggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("email").is(authenticatedUser)),
                Aggregation.group("$category").count().as("count"),
                Aggregation.project("count").and("_id").as("categoryName").andExclude("_id")
        );

        AggregationResults<CategoryCount> categoryCountAggregationResults =
                mongoOperations.aggregate(categoryAggregation, "customer", CategoryCount.class);

        List<CategoryCount> categoryCounts = categoryCountAggregationResults.getMappedResults();

        Criteria criteria = Criteria.where("email").is(authenticatedUser);
        Query query = new Query(criteria);

        long totalCustomers = mongoOperations.count(query, "customer");
        System.out.println(totalCustomers + "han");
        if (!categoryCounts.isEmpty()) {
            snapShots.setCustomerCategoryCount(categoryCounts);
            snapShots.setTotalNumberOfCustomer(totalCustomers);
        }

        //Customer Task SnapShot
        Aggregation customerTaskAggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("userEmail").is(authenticatedUser)),
                Aggregation.match(Criteria.where("status").in("TODO", "DONE")),
                Aggregation.group("$status").count().as("count"),
                Aggregation.project("count").and("_id").as("name").andExclude("_id")
        );
        AggregationResults<TaskCount> customerTaskAggregationResults =
                mongoOperations.aggregate(customerTaskAggregation, "customerTask", TaskCount.class);

        List<TaskCount> taskstatusCounts = customerTaskAggregationResults.getMappedResults();
        int totaltaskCount = 0;
        for (TaskCount count : taskstatusCounts) {
            totaltaskCount += count.getCount();
        }
        System.out.println(totaltaskCount);
        if (!taskstatusCounts.isEmpty()){
            snapShots.setTaskCount(taskstatusCounts);
            snapShots.setTotaltaskCount(totaltaskCount);
        }

        //Customer Stage SnapShots
        Aggregation stageAggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("email").is(authenticatedUser)),
                Aggregation.group("$customerStage").count().as("count"),
                Aggregation.project("count").and("_id").as("stageName").andExclude("_id")
        );

        AggregationResults<StageCount> stageCountAggregationResults =
                mongoOperations.aggregate(stageAggregation, "customer", StageCount.class);

        List<StageCount> stageCounts = stageCountAggregationResults.getMappedResults();
        if (!stageCounts.isEmpty()) {
            snapShots.setCustomerStageCount(stageCounts);
        }


        //Project ADHoc SnapShots
        Aggregation projectAggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("email").is(authenticatedUser)),
                Aggregation.match(
                        Criteria.where("Status").in("Pending", "doing")
                ),
                Aggregation.group("$Status").count().as("count"),
                Aggregation.project("count").and("_id").as("name").andExclude("_id")
        );
        AggregationResults<AdHocCount> projectAggregationResults =
                mongoOperations.aggregate(projectAggregation, "adHocProject", AdHocCount.class);

        List<AdHocCount> statusCounts = projectAggregationResults.getMappedResults();
        int totalProjectCount = 0;
        for (AdHocCount count : statusCounts) {
            totalProjectCount += count.getCount();
        }
        System.out.println(totalProjectCount);
        if (!statusCounts.isEmpty()) {
            snapShots.setAdHocCount(statusCounts);
            snapShots.setTotalNumberOfProjects(totalProjectCount);
        }

        if (snapShots!=null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(snapShots);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("No Data Found");
        }
    }
}
