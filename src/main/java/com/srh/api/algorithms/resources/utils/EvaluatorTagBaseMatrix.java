package com.srh.api.algorithms.resources.utils;

import com.srh.api.algorithms.math.Coordinate;
import com.srh.api.algorithms.math.EuclidianDistance;
import com.srh.api.algorithms.math.MathUtil;
import com.srh.api.algorithms.resources.basedcontent.EvaluatorProfileMatrix;
import com.srh.api.model.*;
import com.srh.api.service.ItemRatingService;
import com.srh.api.service.ProjectService;
import com.srh.api.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EvaluatorTagBaseMatrix extends BaseMatrix {
    // USADA PARA A TRANSIÇÃO ENTRE CONTEUDO E COLABORATIVA NA FILTRAGEM CASCADE/COMBINAÇÃO SEQUENCIAL.
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ItemRatingService itemRatingService;

    @Autowired
    private TagService tagService;

    public void build(Integer projectId) {
        project = projectService.find(projectId);
        evaluators = project.getEvaluators();
        tags = tagService.findAll();

        rowSize = evaluators.size();
        colSize = tags.size();
        
        content = new Double[rowSize][colSize];

        //generateMatrix();
    }

    public void addProfiles(Evaluator evaluator, EvaluatorProfileMatrix evaluatorProfileMatrix){
        // Copia o avaragerow (o perfil em si) para o msm row que está o evaluator
        for (int i = 0; i< colSize; i++){
            content[evaluatorProfileMatrix.getEvaluatorRow()][i] 
                = evaluatorProfileMatrix.getAvarageRow()[i];
        }
    }

    public Double[] getSimilarityArray(Evaluator evaluator,Integer precision){
        Double[] calc = new Double[rowSize];
        int evaIndex = evaluators.indexOf(evaluator);
        List<Coordinate> cordsList;
        EuclidianDistance eDistance = new EuclidianDistance();

        for(int i =0; i< rowSize; i++){
            cordsList = new ArrayList<>();
            for (int j = 0; j<colSize; j++){
                if (!isNonZero(content[evaIndex][j])) continue;
                if (!isNonZero(content[i][j])) continue;
                cordsList.add(RecommendationUtils.buildCoordinate(
                    content[evaIndex][j], content[i][j]));
            }
            //System.out.println(evaIndex + " - " + i + " " + eDistance.calc(cordsList) + " " + MathUtil.calculateSimilarity(eDistance.calc(cordsList)));
            calc[i] = RecommendationUtils.roundValue(MathUtil.calculateSimilarity(eDistance.calc(cordsList)),precision);
        }

        return calc;
    }

    private boolean isNonZero(Double value){
        if(value == null || value == 0.0) return false;
        else return true;
    }

    //Testing purposes only
    public void dumpContentMatrix(){
        for (int i=0; i < rowSize; i++){
            for (int j=0; j < colSize; j++){
                System.out.print(content[i][j]+",\t");
            }
            System.out.println("");
        }
    }
}

