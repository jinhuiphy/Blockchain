# Blockchain

Blockchain has a simple interpretation: it's just a chain of blocks. It represents a sequence of data that you can't break in the middle; you can only append new data at the end of it. All the blocks in the blockchain are chained together.

Check out [this great video about the blockchain](https://www.youtube.com/watch?v=bBC-nXj3Ng4). It uses a different approach to reach the final result of the project, which is cryptocurrencies, but it explains the blockchain pretty well.

To be called a blockchain, every block must include the hash of the previous block. Other fields of the block are optional and can store various information. The hash of a block is a hash of all fields of a block. So, you can just create a string containing every element of a block and then get the hash of this string.

Note that if you change one block in the middle, the hash of this block will also change. and the next block in the chain would no longer contain the hash of the previous block. Therefore, it’s easy to check that the chain is invalid.

When the project done, there should be a microcosm where virtual miners earn cryptocurrency and exchange messages and transactions using blockchain.
