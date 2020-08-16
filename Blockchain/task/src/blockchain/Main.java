package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    private final static int POOL_SIZE = 1;

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int numOfZeros = 0;
        BlockChain blockChain = BlockChain.getInstance();

        for (int i = 0; i < 5; i++) {
            ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);
            List<Miner> callables = new ArrayList<>();
            for (int j = 0; j < POOL_SIZE; j++) {
                Miner miner = new Miner(blockChain, numOfZeros);
                callables.add(miner);
            }
            Block block = executor.invokeAny(callables);
            blockChain.addBlock(block);
            executor.shutdownNow();

            System.out.println(block);

            if (i == 0) {
                System.out.println("Block data: no messages");
            } else {
                System.out.println("Block data:");

                for (var message : blockChain.getMessages()) {
                    System.out.println(message);
                }
            }

            System.out.println("Block was generating for " + block.getTimeCost() + " seconds");

            if (block.getTimeCost() < 30) {
                numOfZeros++;
                System.out.printf("N was increased to %d%n%n", numOfZeros);
            } else if (block.getTimeCost() > 60) {
                numOfZeros--;
                System.out.printf("N was decreased to %d%n%n", numOfZeros);
            } else {
                System.out.printf("N stays the same%n%n");
            }
        }
    }
}
