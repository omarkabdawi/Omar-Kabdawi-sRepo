# OmarKabdawi-sRepo
Storage for projects worked on by Omar Kabdawi


This is a Java Project that Simulates a Computer Architecure not unlike the MIPS Architecture. The only main differences are that they are pipelined in the way you will find in the cycles pdf

It supports the follwoing instructions: 

ADD R1 R2 R3 (R1 = R2 + R3)
SUB R1 R2 R3 (R1 = R2 - R3)
MULI R1 R2 IMM (R1 = R2 * IMM)
ADDI R1 R2 IMM (R1 = R2 + IMM)
BNE R1 R2 IMM (IF(R1 != R2) {PC = PC+1+IMM })
ANDI R1 R2 IMM (R1 = R2 & IMM)
ORI R1 R2 IMM (R1 = R2 | IMM)
J ADDRESS (PC = PC[31:28] || ADDRESS)
SLL R1 R2 SHAMT (R1 = R2 << SHAMT)
SRL R1 R2 SHAMT (R1 = R2 >>> SHAMT)
LW R1 R2 IMM (R1 = MEM[R2 + IMM])
SW R1 R2 IMM (MEM[R2 + IMM] = R1)
