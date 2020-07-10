package blockchain;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BlockChain blockChain = new BlockChain();
        for (int i = 0; i < 10; i++) {
            blockChain.generate();
        }
        for (int i = 0; i < 5; i++) {
            Block block = blockChain.getBlockList().get(i);
            System.out.println(block.toString() + "\nHash of the block: \n" + StringUtil.applySha256(block) + "\n");
        }
    }
}
