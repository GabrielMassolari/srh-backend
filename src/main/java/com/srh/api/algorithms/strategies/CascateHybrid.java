package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.math.CellPosition;
import com.srh.api.algorithms.resources.*;
import com.srh.api.algorithms.resources.basedcontent.EvaluatorProfileMatrix;
import com.srh.api.algorithms.resources.basedcontent.ItemTagMatrix;
import com.srh.api.algorithms.resources.utils.BasicBaseMatrix;
import com.srh.api.algorithms.resources.utils.EvaluatorTagBaseMatrix;
import com.srh.api.algorithms.resources.utils.RecommendationUtils;
import com.srh.api.algorithms.resources.utils.RecommendationsByEvaluator;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CascateHybrid implements RecommendationAlgorithm {
    @Autowired
    private BasicBaseMatrix primaryMatrix;

    @Autowired
    private EvaluatorTagBaseMatrix evaluatorTagMatrix;

    @Autowired
    private ItemTagMatrix itemTagMatrix;

    @Autowired
    private RecommendationUtils recommendationUtils;

    private final List<RecommendationsByEvaluator> recommendationsByEvaluators = new ArrayList<>();
    private List<CellPosition> recommendationsPositions = new ArrayList<>();
    private Double passingScore;
    private Integer decimalPrecision;
    private LocalDateTime startRecommendation;

    @Override
    public List<RecommendationsByEvaluator> calc(RecommendationForm form) {
        // Passo 1, pegar os dados do projeto e montar matrizes iniciais
        passingScore = form.getPassingScore();
        decimalPrecision = form.getDecimalPrecision();

        //buildBasicMatrix(form.getProjectId());
        primaryMatrix.build(form.getProjectId());
        itemTagMatrix.build(primaryMatrix.getItems());

        evaluatorTagMatrix.build(form.getProjectId());

        // Passo 2, Calcular o perfil de todo mundo e reunir em um lugar só.
        for(Evaluator evaluator: primaryMatrix.getEvaluators()) {
            EvaluatorProfileMatrix evaluatorProfileMatrix = mountEvaluatorProfile(evaluator);
            evaluatorTagMatrix.addProfiles(evaluator, evaluatorProfileMatrix);
            // matriz retornada por evaluatorProfileMatrix aqui encima é o calculo de profile de TODO MUNDO (profile = perfil = média da content.)
            // Contém métodos para pegar a fileira do evaluator e o array do perfil dele!
            // o evaluator-tag Matrix recebe com todo prazer e armazena. 
            
            //RecommendationsByEvaluator recommendationsByEvaluator = calculateRecommendationByEvaluator(
                    //evaluatorProfileMatrix, itemTagMatrix, evaluator);
            //recommendationsByEvaluators.add(recommendationsByEvaluator);
        }
        //DEBUG
        /*for (int i=0; i < primaryMatrix.getRowSize(); i++){
            for (int j=0; j < primaryMatrix.getColSize(); j++){
                System.out.print(primaryMatrix.getContent()[i][j]+",\t");
            }
            System.out.println("");
        }
        evaluatorTagMatrix.dumpContentMatrix();
        Double[] debug = evaluatorTagMatrix.getSimilarityArray(primaryMatrix.getEvaluators().get(1),decimalPrecision);
        for (int j=0; j < debug.length; j++){
            System.out.print(debug[j]+",\t");
        }*/
        //Checado e passo 1 e 2 estão ok.
        
        // Passo 3 - Fazer a filtragem colaborativa nisso.
        // Plano: checar o primary matrix por valores vazios. nao existente estão null lá. mas checa por 0 tb.
        //      Feito isso, sabemos o produto E o evaluator que precisa
        //      Pega a similarity row, detecta quem comprou o produto e as notas deles
        //      Segue o slide, e faz o que é feito lá.
        //      Finaliza com o save. comando é
        //RecommendationUtils recUtils = new RecommendationUtils();
        //recUtils.buildRecommendation(score, startRecommendationTime, algorithmId, item, evaluator, project)

        List<Recommendation> lRecommendations;

        for (int i=0; i < primaryMatrix.getRowSize(); i++){
            lRecommendations = new ArrayList<>();
            Evaluator currentEvaluator = primaryMatrix.getEvaluators().get(i);
            for (int j=0; j < primaryMatrix.getColSize(); j++){
                if(!isNonZero(primaryMatrix.getContent()[i][j])) {
                    // Precisa estimar nota. trabalha!
                    startRecommendation = LocalDateTime.now();
                    Double[] similarityFromEvaluator = evaluatorTagMatrix.getSimilarityArray(currentEvaluator, decimalPrecision);
                    Double sumOfSimiTimesScore = 0.0;
                    Double sumOfSimiEvaluator  = 0.0;
                    for(int k=0; k<primaryMatrix.getRowSize();k++){
                        // J é constante, pois ele definiu o produto. K vai varrer a coluna para ver quem já comprou ele.
                        if (isNonZero(primaryMatrix.getContent()[k][j])){
                            // Comprou o produto! hora do cálculo
                            sumOfSimiTimesScore += (similarityFromEvaluator[k] * primaryMatrix.getContent()[k][j]);
                            sumOfSimiEvaluator  +=  similarityFromEvaluator[k];
                        }
                    }
                    // Percorrida as notas. hora de soltar a nota prevista para a recomendação: 
                    Double score;
                    if (sumOfSimiEvaluator == 0.0) 
                         score = 0.0;
                    else score = sumOfSimiTimesScore/ sumOfSimiEvaluator;

                    Recommendation reco = recommendationUtils.buildRecommendation(
                        score, startRecommendation, 5, primaryMatrix.getItems().get(j), currentEvaluator, primaryMatrix.getProject());
                    CellPosition cellPosition = RecommendationUtils.buildCellPosition(i, j);
                        //DEBUG
                    //System.out.println(score + " - " + i + " " + j);
                    if (reco.getWeight() >= passingScore){
                        lRecommendations.add(reco);
                        recommendationsPositions.add(cellPosition);
                    }
                }
            }
            // mesma pessoa ainda.

            // PASSO 4 - Voltar ao padrão do projeto para que as recomendações sejam salvas e retornadas corretamente. 
            RecommendationsByEvaluator rEvaluator = new RecommendationsByEvaluator();
            rEvaluator.setEvaluator(currentEvaluator);
            rEvaluator.setMatrixId(recommendationUtils.getNewMatrixIndex(primaryMatrix.getProject()));
            rEvaluator.setRecommendations(lRecommendations);
            
            recommendationsByEvaluators.add(rEvaluator);

        }

        recommendationUtils.defineNewMatrixId(form.getProjectId());
        return recommendationsByEvaluators;
    }

    private boolean isNonZero(Double value){
        if(value == null ) return false; //|| value == 0.0) return false;
        else return true;
    }
    private EvaluatorProfileMatrix mountEvaluatorProfile(Evaluator evaluator) {
        EvaluatorProfileMatrix evaluatorProfileMatrix = new EvaluatorProfileMatrix();
        evaluatorProfileMatrix.build(evaluator, primaryMatrix, itemTagMatrix);
        return evaluatorProfileMatrix;
    }

    @Override
    public List<CellPosition> getRecommendationsPositions() {
        return recommendationsPositions;
    }
}
