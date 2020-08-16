package blockchain;

import java.util.concurrent.Callable;

public class Miner implements Callable<Block> {
    private final BlockChain blockChain;
    private final int numOfZeros;

    public Miner(BlockChain blockChain, int numOfZeros) {
        this.blockChain = blockChain;
        this.numOfZeros = numOfZeros;
    }

    @Override
    public Block call() throws Exception {
        // generate new block
        blockChain.reset();
        Block block = blockChain.generateBlock(numOfZeros);
        block.setMinerId(Thread.currentThread().getId());
        return block;
    }
}
