package com.srh.api.repository;

import com.srh.api.model.Algorithm;
import com.srh.api.model.ItemRating;
import com.srh.api.model.Project;
import com.srh.api.model.Recommendation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends PagingAndSortingRepository<Recommendation, Integer> {
    @Query(
            value = "SELECT * FROM recommendation r \n" +
                    "\tWHERE (\n" +
                    "    SELECT r.matrix_id \n" +
                    "    FROM recommendation r\n" +
                    "    INNER JOIN item i ON i.id = r.item_id\n" +
                    "    INNER JOIN project p ON p.id = i.project_id\n" +
                    "    WHERE r.algorithm_id = ?1 AND p.id = ?2\n" +
                    "    ORDER BY r.date DESC LIMIT 1) = r.matrix_id AND\n" +
                    "    r.evaluator_id = ?3",
            nativeQuery = true
    )
    List<Recommendation> listLastRecommendationsByAlgorithmId(Integer algorithmId, Integer projectId, Integer evaluatorId);

    List<Recommendation> findByAlgorithm(Algorithm algorithm);
    List<Recommendation> findByMatrixId(Integer matrixId);
    List<Recommendation> findByItem(Integer itemId);

    @Query(value="SELECT * FROM RECOMMENDATION WHERE evaluator_id = ?1 AND item_id = ?2 AND algorithm_id = ?3", nativeQuery = true)
    Optional<Recommendation> findByEvaluatorAndItem(Integer evaluatorId, Integer itemId, Integer algorithmId);
}
