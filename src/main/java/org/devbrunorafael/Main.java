package org.devbrunorafael;

public class Main {
    public static void main(String[] args) {
        String[] nodes = {"S", "A", "B", "C", "D", "E", "T"};

        int[][] adjacentMatrix = {
                //         S,  A,  B,  C,  D,  E,  T   (Nós de Destino)
                /* S */  { 0, 30, 10,  0,  0, 20,  0 },
                /* A */  { 0,  0, 30, 15,  0,  0, 10 },
                /* B */  { 0,  0,  0,  0, 20,  0,  0 },
                /* C */  { 0,  0,  0,  0,  0,  0, 15 },
                /* D */  { 0,  0,  0,  0,  0,  0, 50 },
                /* E */  { 0,  0,  0,  0, 35,  0,  0 },
                /* T */  { 0,  0,  0,  0,  0,  0,  0 }
        };

        FordFulkerson solver = new FordFulkerson(adjacentMatrix);

        int sourceNode = 0;
        int sinkNode = 6;

        int maxFlow = solver.findMaxFlow(sourceNode, sinkNode);

        System.out.println("O fluxo máximo de " + nodes[sourceNode] + " para " + nodes[sinkNode] + " é: " + maxFlow);
    }
}