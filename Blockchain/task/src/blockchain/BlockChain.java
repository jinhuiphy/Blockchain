package blockchain;

import java.util.*;

public class BlockChain {

    private static final BlockChain instance = new BlockChain();

    private BlockChain() {}

    public static BlockChain getInstance() {
        return instance;
    }

    private final List<Block> blockList = new ArrayList<>();

    private final List<String> messages = new ArrayList<>();

    public Block generateBlock(int numOfZeros) {
        int id = blockList.size() + 1;
        String prevHash = blockList.isEmpty() ? "0" : blockList.get(blockList.size() - 1).generateHash();
        Block block = new Block(id, prevHash);
        if (!blockList.isEmpty()) {
            communicate();
        }
        // Set magic number
        String hash;
        int magicNumber;
        Random rand = new Random(Integer.MAX_VALUE);
        do {
            magicNumber = rand.nextInt();
            hash = block.generateHashWith(magicNumber);
        } while (!hash.matches(String.format("0{%d}\\w*", numOfZeros)));

        block.setMagicNumber(magicNumber);

        // Set cost time
        long endTimeStamp = new Date().getTime();
        long timeCost = (endTimeStamp - block.getTimeStamp()) / 10;
        block.setTimeCost(timeCost);
        return block;
    }

    public boolean validate() {
        for (int i = 0; i < blockList.size() - 1; i++) {
            String hashOfBlock = StringUtil.applySha256(blockList.get(i).toString());
            if (!hashOfBlock.equals(blockList.get(i + 1).getPrevHash())) {
                return false;
            }
        }
        return true;
    }

    public void communicate() {
        messages.add("Test");
    }

    public void reset() {
        messages.clear();
    }

    public List<String> getMessages() {
        return messages;
    }

    public void addBlock(Block block) {
        blockList.add(block);
    }

    public void print(int num) {
        for (int i = 0; i < num; i++) {
            System.out.println(blockList.get(i) + "\n");
        }
    }

    public int getSize() {
        return blockList.size();
    }
}

class Block {
    private final int id;
    private final long timeStamp = new Date().getTime();
    private final String prevHash;
    private int magicNumber;
    private long minerId;
    private long timeCost;

    public Block(int id, String prevHash) {
        this.id = id;
        this.prevHash = prevHash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public void setMinerId(long minerId) {
        this.minerId = minerId;
    }

    public void setTimeCost(long timeCost) {
        this.timeCost = timeCost;
    }

    public long getTimeCost() {
        return timeCost;
    }

    public String generateHash() {
        return StringUtil.applySha256(prevHash + id + timeStamp + magicNumber);
    }

    public String generateHashWith(int magicNumber) {
        return StringUtil.applySha256(prevHash + id + timeStamp + magicNumber);
    }

    @Override
    public String toString() {
        return "Block:" +
                "\nCreated by miner # " + minerId +
                "\nId: " + id +
                "\nTimestamp: " + timeStamp +
                "\nMagic number: " + magicNumber +
                "\nHash of the previous block:\n" + prevHash +
                "\nHash of the block:\n" + generateHash();
    }
}
