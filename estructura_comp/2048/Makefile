CC = gcc
ASM = yasm
CFLAGS = -Wall -g -mincoming-stack-boundary=3
ASMFLAGS = -f elf64 -g dwarf2
LDFLAGS = -no-pie 

NAME = game
C_SRC = 2Kp1c-es.c
ASM_SRC = 2Kp1-es.asm

C_OBJ = $(C_SRC:.c=.o)
ASM_OBJ = $(ASM_SRC:.asm=.o)

all : $(NAME)

$(NAME): $(C_OBJ) $(ASM_OBJ)
	$(CC) $(LDFLAGS) -o $@ $(C_OBJ) $(ASM_OBJ)

%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

%.o: %.asm
	$(ASM) $(ASMFLAGS) $< -o $@

clean:
	rm -f $(NAME) $(C_OBJ) $(ASM_OBJ)

run: $(NAME)
	./$(NAME)

re: clean all

.PHONY: all clean run re
