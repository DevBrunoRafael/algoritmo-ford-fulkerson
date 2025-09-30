package org.devbrunorafael;

import java.util.Stack;

public class FordFulkerson {

    private final int numVertices;

    private int[][] residualGraph;

    /**
     * Construtor da classe.
     * @param graph A matriz de adjacência do grafo original com as capacidades.
     */
    public FordFulkerson(int[][] graph) {
        this.numVertices = graph.length;
        this.residualGraph = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(graph[i], 0, this.residualGraph[i], 0, numVertices);
        }
    }

    /**
     * Implementação da Busca em Profundidade (DFS) para encontrar um caminho de aumento
     * no grafo residual.
     * @param source O nó de origem.
     * @param sink O nó sumidouro (destino).
     * @param parent Um array para armazenar o caminho encontrado. parent[i] armazena o nó
     * que veio antes de 'i' no caminho.
     * @return true se um caminho de 'source' para 'sink' for encontrado, senão false.
     */
    private boolean dfs(int source, int sink, int[] parent) {
        boolean[] visited = new boolean[numVertices];
        Stack<Integer> stack = new Stack<>();

        stack.push(source);
        visited[source] = true;
        parent[source] = -1;

        while (!stack.isEmpty()) {
            int u = stack.pop();

            for (int v = 0; v < numVertices; v++) {
                if (!visited[v] && residualGraph[u][v] > 0) {
                    stack.push(v);
                    parent[v] = u;
                    visited[v] = true;

                    if (v == sink) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Método principal que calcula o fluxo máximo de uma fonte para um sumidouro.
     * @param source O nó de origem.
     * @param sink O nó sumidouro (destino).
     * @return O valor do fluxo máximo.
     */
    public int findMaxFlow(int source, int sink) {
        int[] parent = new int[numVertices];

        int maxFlow = 0;

        while (dfs(source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }
}