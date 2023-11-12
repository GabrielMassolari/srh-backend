package com.srh.api.service;


import com.srh.api.model.ItemRating;
import com.srh.api.model.Recommendation;
import com.srh.api.model.RecommendationRating;
import com.srh.api.model.Tuple;
import com.srh.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gurobi.*;

import java.util.*;

@Service
public class FairnessRecommendationService {
    @Autowired
    private EvaluatorRepository evaluatorRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemRatingRepository itemRatingRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private RecommendationRatingRepository recommendationRatingRepository;

    public double[][] getFairnessRecomendation(Integer ProjectId, Integer algorithmId, Integer list_number, ArrayList<Integer>[] Groups) {
        final int usuarios = (int) evaluatorRepository.count();
        final int linha = usuarios;
        final int coluna = (int) itemRepository.count();
        int n_groups = Groups.length;

        double[][] xoriginal = new double[linha][coluna];
        double[][] xestimada = new double[linha][coluna];
        double[][] xavaliacao = new double[linha][coluna];

        double[][][] lista_x = new double[list_number][][];

        int[] celulasConhecidas = new int[usuarios];
        int[] celulasNaoConhecidas = new int[usuarios];
        double[] difMediaUsuarios = new double[usuarios];


        double calculandoli[] = new double[usuarios];

        double li[] = new double[usuarios];
        double totali = 0;
        double mediali = 0;
        double rindv = 0;

        // Geração do G
        Map<Integer, ArrayList<Integer>> G = new HashMap<>();
        for (int i = 0; i < Groups.length; i++) {
            G.put(i + 1, Groups[i]);
        }

        // Geração do G_index
        Map<Integer, ArrayList<Integer>> G_index = new HashMap<>();
        for (int i = 0; i < Groups.length; i++) {
            // Cria uma nova lista para armazenar os valores decrementados
            ArrayList<Integer> decrementedValues = new ArrayList<>();
            for (int val : Groups[i]) {
                decrementedValues.add(val - 1); // Decrementa cada valor
            }
            // Adiciona a lista de valores decrementados ao mapa com a chave sendo i + 1
            G_index.put(i + 1, decrementedValues);
        }

        //Geração do Users_g

        HashMap<Integer, List<String>> users_g = new HashMap<>();

        for (Map.Entry<Integer, ArrayList<Integer>> entry : G.entrySet()) {
            Integer groupNumber = entry.getKey();
            ArrayList<Integer> users = entry.getValue();

            List<String> userIdentifiers = new ArrayList<>();
            for (Integer user : users) {
                userIdentifiers.add("U" + user);
            }

            users_g.put(groupNumber, userIdentifiers);
        }


        // Geração do ls_G
        HashMap<Integer, List<String>> ls_g = new HashMap<>();

        for (Map.Entry<Integer, ArrayList<Integer>> entry : G.entrySet()) {
            Integer groupNumber = entry.getKey();

            List<String> lsIdentifier = new ArrayList<>();
            for(int i = 0; i < list_number; i++) {
                lsIdentifier.add("l" + (i+1));
            }

            ls_g.put(groupNumber, lsIdentifier);
        }


        Iterable<ItemRating> avaliacoesItens =
                itemRatingRepository.findAll();
        Iterable<Recommendation> recomendacoes =
                recommendationRepository.findAll();

        //MATRIZ ORIGINAL
        Optional<ItemRating> avaliacaoResultado;
        ItemRating avaliacao;
        for(int l = 0; l < linha; l++) {
            for(int c = 0; c < coluna; c++){
                avaliacaoResultado = itemRatingRepository.findByEvaluatorAndItem(l+1, c+1);
                if (avaliacaoResultado.isPresent()) {
                    avaliacao = avaliacaoResultado.get();
                    xoriginal[l][c] = avaliacao.getScore();
                    celulasConhecidas[l]++;
                }else {
                    xoriginal[l][c] = 0;
                    celulasNaoConhecidas[l]++;
                }
            }
        }

        //GERAR RECOMENDACAO

        //MATRIZ ESTIMADA - Prediction
        Optional<Recommendation> recomendacaoResultado;
        Recommendation recomendacao;
        for(int l = 0; l < linha; l++) {
            for(int c = 0; c < coluna; c++){
                if(xoriginal[l][c] == 0){
                    recomendacaoResultado = recommendationRepository.findByEvaluatorAndItem(l+1, c+1, algorithmId);
                    if(recomendacaoResultado.isPresent()){
                        recomendacao = recomendacaoResultado.get();
                        xestimada[l][c] = recomendacao.getWeight();
                    }else{
                        //NAO TEM RECOMENDACAO - AVALIAR O QUE RETORNAR
                        //TALVEZ LANÇAR EXCECAO
                        xestimada[l][c] = 0;
                    }
                }else{
                    xestimada[l][c] = xoriginal[l][c];
                }
            }
        }

        //Gerar Xavalicao
        Optional<RecommendationRating> avaliacaoRecomendacaoResultado;
        RecommendationRating avaliacaoRecomendacao;
        for(int l = 0; l < linha; l++) {
            for(int c = 0; c < coluna; c++){
                if(xoriginal[l][c] == 0){
                    recomendacaoResultado = recommendationRepository.findByEvaluatorAndItem(l+1, c+1, algorithmId);

                    if(recomendacaoResultado.isPresent()){
                        recomendacao = recomendacaoResultado.get();
                        int recomendacaoId = recomendacao.getId();
                        avaliacaoRecomendacaoResultado = recommendationRatingRepository.findByRecommendationId(recomendacaoId);

                        if(avaliacaoRecomendacaoResultado.isPresent()){
                            avaliacaoRecomendacao = avaliacaoRecomendacaoResultado.get();
                            xavaliacao[l][c] = avaliacaoRecomendacao.getScore();
                        }
                    }
                }else{
                    xavaliacao[l][c] = xoriginal[l][c];
                }
            }
        }

        double auxLi = 0;
        double auxMedia = 0;
        int qtdRepeticao = 0;
        //CALCULANDO LI ( U = USUARIO DA POSIÇÃO ZERO (0))
        for(int l = 0; l < linha; l++){
            for(int c = 0; c < coluna; c++){
                if(xoriginal[l][c] == 0) {
                    auxLi = auxLi + (Math.pow(xestimada[l][c] - xavaliacao[l][c], 2) / 4);
                    auxMedia = xestimada[l][c] - xavaliacao[l][c];
                    qtdRepeticao++;
                }
            }
            li[l] = auxLi / qtdRepeticao;
            difMediaUsuarios[l] = auxMedia / qtdRepeticao;
            auxMedia = 0;
            auxLi = 0;
            qtdRepeticao = 0;
        }

//        int u = 0;
//        while (u < coluna) {
//            //COMPRANDO OS ERROS DA MATRIZE ESTIMADAS - A MATRIZ ORIGINAL
//            for (int lo = 0; lo < xoriginal.length; lo++) {
//                //int u = 0;
//                if (xoriginal[lo][u] == 0) {
//                    calculandoli[u] = calculandoli[u] + (Math.pow(xavaliacao[lo][u] - xestimada[lo][u], 2));
//                }
//            }
//            u++;
//        }

//        //ENCONTRANDO O li DA JUSTIÇA INDIVIDUAL DE CADA USUÁRIO
//        for (int i = 0; i < usuarios; i++) {
//            li[i] = calculandoli[i] / celulasConhecidas[i];
//            //System.out.println("LI[" + i + "]: " + li[i]);
//        }

        double[][] x1 = new double[linha][coluna];
        for(int i = 0; i < list_number; i++){
            x1 = new double[linha][coluna];
            for(int l = 0; l < linha; l++){
                for(int c = 0; c < coluna; c++){
                    if (xoriginal[l][c] == 0){
                        Random random = new Random();

                        double numeroAleatorio = random.nextDouble();
                        double numeroFinal = 0;

                        // Calcule o número aleatório ajustado para o intervalo desejado
                        if(difMediaUsuarios[l] >= 0){
                            numeroFinal = xestimada[l][c] + (numeroAleatorio * li[l]);
                        }else {
                            numeroFinal = xestimada[l][c] - (numeroAleatorio * li[l]);
                        }
                        x1[l][c] = numeroFinal;
                    }else {
                        x1[l][c] = xoriginal[l][c];
                    }
                }
            }
            lista_x[i] = x1;
        }


        double lix[] = new double[usuarios];
        double[][] list_lix = new double[list_number][usuarios];
        auxLi = 0;
        auxMedia = 0;
        qtdRepeticao = 0;
        //CALCULANDO LI ( U = USUARIO DA POSIÇÃO ZERO (0))
        for(int i = 0; i < list_number; i++){
            lix = new double[usuarios];
            for(int l = 0; l < linha; l++){
                for(int c = 0; c < coluna; c++){
                    if(xoriginal[l][c] == 0) {
                        auxLi = auxLi + (Math.pow(lista_x[i][l][c] - xavaliacao[l][c], 2) / 4);
                        auxMedia = lista_x[i][l][c] - xavaliacao[l][c];
                        qtdRepeticao++;
                    }
                }
                lix[l] = auxLi / qtdRepeticao;
                difMediaUsuarios[l] = auxMedia / qtdRepeticao;
                auxMedia = 0;
                auxLi = 0;
                qtdRepeticao = 0;
            }
            list_lix[i] = lix;
        }

        List<Map<Tuple, Double>> listPreferences = new ArrayList<>();

        for(int g = 0; g < n_groups; g++){
            listPreferences.add(new HashMap<Tuple, Double>());
            for(int u: G_index.get(g+1)){
                for(int i = 0; i < ls_g.get(g+1).size(); i++){
                    String user_n = ("U" + (u+1));
                    listPreferences.get(g).put(new Tuple(user_n, ls_g.get(g+1).get(i)), list_lix[i][u]);
                }
            }
        }

        try{
            GRBEnv env = new GRBEnv(true);
            env.set("logFile", "mip.log");
            env.start();

            GRBModel m = new GRBModel(env);

            //HashMap<Tuple, GRBVar> xVars = new HashMap<>();
            ArrayList<GRBVar[][]> grbVarList = new ArrayList<>();


            //Adicao variaveis
            for(int i = 0; i < n_groups; i++){
                int qtdUser = users_g.get(i+1).size();
                int qtdL = ls_g.get(i+1).size();
                GRBVar[][] var = new GRBVar[qtdUser][qtdL];
                grbVarList.add(var);
            }

            //Preencher Variaveis
            for(int g = 0; g < n_groups; g++){
                int qtdUser = users_g.get(g+1).size();
                int qtdL = ls_g.get(g+1).size();
                for(int i = 0; i < qtdUser; i++){
                    for(int j = 0; j < qtdL; j++){
                        grbVarList.get(g)[i][j] = m.addVar(0.0, 1.0, 0.0, GRB.BINARY, "x_" + i + "_" + j);
                    }
                }
            }

            ArrayList<GRBLinExpr> grbLinExprList = new ArrayList<>();
            ArrayList<GRBLinExpr> grbConstList = new ArrayList<>();

            for(int g = 0; g < n_groups; g++){
                int qtdUser = users_g.get(g+1).size();
                int qtdL = ls_g.get(g+1).size();
                grbLinExprList.add(new GRBLinExpr());
                grbConstList.add(new GRBLinExpr());
                for(int i = 0; i < qtdUser; i++){
                    for(int j = 0; j < qtdL; j++){
                        grbLinExprList.get(g).addTerm(list_lix[j][G_index.get(g+1).get(i)], grbVarList.get(g)[i][j]);
                        grbConstList.get(g).addTerm(1, grbVarList.get(g)[i][j]);
                    }
                    String constrName = g + "_row_sum_" + i ;
                    m.addConstr(grbLinExprList.get(g), GRB.EQUAL, 1.0, constrName);
                }
            }

            m.update();

            GRBLinExpr LMean = new GRBLinExpr();
            GRBVar LMeanVar = m.addVar(-GRB.INFINITY, GRB.INFINITY, 0, GRB.CONTINUOUS, "avgTotalVar");

            for(int g = 0; g < n_groups; g++){
                LMean.add(grbLinExprList.get(g));
            }

            LMean.multAdd(1.0 / usuarios, LMean);

            m.addConstr(LMeanVar, GRB.EQUAL, LMean, "avgTotalConstr");

            // Variável auxiliar para o quadrado da média das médias
            GRBVar avgTotalSq = m.addVar(0, GRB.INFINITY, 0, GRB.CONTINUOUS, "avgTotalSq");

            // Restrição quadrática para definir avgTotalSq como o quadrado de avgTotalVar
            GRBQuadExpr quad = new GRBQuadExpr();
            quad.addTerm(1.0, LMeanVar, LMeanVar); // adiciona o termo quadrático
            m.addQConstr(quad, GRB.EQUAL, avgTotalSq, "avgTotalSqConstr");

            // Função objetivo: Minimizar a variância das médias

            GRBLinExpr variance = new GRBLinExpr();
            variance.addTerm(1.0, avgTotalSq);
            variance.addTerm(-1.0, LMeanVar);
            variance.addTerm(-1.0, LMeanVar);
            m.setObjective(variance, GRB.MINIMIZE);

            // Otimizar o modelo
            m.optimize();





/*            ArrayList<GRBVar> grbAuxVar = new ArrayList<>();
            for(int g = 0; g < n_groups; g++){
                GRBVar auxVar = m.addVar(-GRB.INFINITY, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "diff"+g);
                grbAuxVar.add(auxVar);
                m.addConstr(grbAuxVar.get(g), GRB.EQUAL, grbLinExprList.get(g), "const_diff"+g);
            }

            m.update();

            GRBQuadExpr Rgrp = new GRBQuadExpr();

            for(int g = 0; g < n_groups; g++){
                Rgrp.addTerm(1.0/n_groups, grbAuxVar.get(g), grbAuxVar.get(g));
            }

            m.update();

            m.setObjective(Rgrp);

            m.optimize();*/

            for(int g = 0; g < n_groups; g++){
                int qtdUser = users_g.get(g+1).size();
                int qtdL = ls_g.get(g+1).size();
                System.out.println("--GRUPO " + g);
                for(int i = 0; i < qtdUser; i++){
                    for(int j = 0; j < qtdL; j++){
                        System.out.print(grbVarList.get(g)[i][j].get(GRB.DoubleAttr.X) + " ");
                    }
                    System.out.println("");
                }
            }
        }catch (GRBException e) {
            System.out.println("Error code: " + e.getErrorCode() + ". " +
                    e.getMessage());
        }


        System.out.println(G);
        System.out.println(G_index);
        System.out.println(users_g);
        System.out.println(ls_g);

        System.out.println("Antes: " + Arrays.toString(li));
        System.out.println("Dps: " + Arrays.toString(lix));

        return list_lix;
    }



}
