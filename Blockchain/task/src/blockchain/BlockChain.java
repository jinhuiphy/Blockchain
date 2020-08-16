package blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlockChain {
    private final List<Block> blockList = new ArrayList<>();

    public void generate() {
        int id = blockList.size() + 1;
        long timeStamp= new Date().getTime();
        String hashOfPrevious;
        if (blockList.size() == 0) {
            hashOfPrevious = "0";
        } else {
            Block previousBlock = blockList.get(blockList.size() - 1);
            hashOfPrevious = StringUtil.applySha256(previousBlock.getId() + previousBlock.getTimeStamp() + previousBlock.getHashOfPreviousBlock());
        }
        Block block = new Block(id, timeStamp, hashOfPrevious);
        blockList.add(block);
    }

    public boolean validate() {
        for (int i = 0; i < blockList.size() - 1; i++) {
            String hashOfBlock = StringUtil.applySha256(blockList.get(i));
            if (!hashOfBlock.equals(blockList.get(i + 1).getHashOfPreviousBlock())) {
                return false;
            }
        }
        return true;
    }

    public List<Block> getBlockList() {
        return blockList;
    }
}

class Block {
    private final int id;
    private final long timeStamp;
    private final String hashOfPreviousBlock;

    public Block(int id, long timeStamp, String hashOfPreviousBlock) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.hashOfPreviousBlock = hashOfPreviousBlock;
    }

    public int getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getHashOfPreviousBlock() {
        return hashOfPreviousBlock;
    }

    @Override
    public String toString() {
        return "Block:" +
                "\nId: " + id +
                "\nTimestamp: " + timeStamp +
                "\nHash of the previous block:\n" + hashOfPreviousBlock;
    }
}
