import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//this is a test change

/**
 * Created by homecomputer on 11/7/15.
 */
public class simulator {
    HashMap<Integer, String> registers = new HashMap<Integer, String>(); // Registers and respective values
    ArrayList<GenInstruction> instructions = new ArrayList<GenInstruction>(); // List of instructions
    long programCounter;

    public static void main(String[] args) {
        System.out.println("Simulation Mode");

        Scanner reader = new Scanner(System.in);
        String line;

        while (reader.hasNext()) {
            line = reader.nextLine();

            if (line.equals("Step")) {

            }
            else if (line.equals("Run")) {

            }
            else {
                System.out.println("Invalid command. Enter Run or Step to continue.");
            }
        }
    }


    private class GenInstruction {
        int opcode;
    }

    /**
     * Instructions needed:
     *
     * lw -
     * jal -
     * move = add -
     * li = lui then ori
     * syscall
     * or -
     * ori -
     * and -
     * beq -
     * addi - 0x00 0x21 rt rd sa
     * addiu -
     * sll -
     * bne -
     * jr -
     */

    /**
     * R Instruction
     * opcode - 000000
     */
    private class RInstruction extends GenInstruction {
        int rs;
        int rt;
        int rd;
        int sa;
        int function;

        public RInstruction(int rs, int rt, int rd, int sa, int function) {
            this.rs = rs;
            this.rt = rt;
            this.rd = rd;
            this.sa = sa;
            this.function = function;
        }
    }

    /**
     * I Instruction
     *
     */
    private class IInstruction extends GenInstruction {
        int rs;
        int rt;
        int immediate;

        public IInstruction (int rs, int rt, int immediate) {
            this.rs = rs;
            this.rt = rt;
            this.immediate = immediate;
        }
    }

    /**
     * J Instruction
     * opcode - 00001x
     */
    private class JInstruction extends GenInstruction {
        long target;

        public JInstruction (long target) {
            this.target = target;
        }
    }

    /**
     * Simulates line by line
     * @param instruction
     */
    public static void simulate(GenInstruction instruction) {
        String instructionType = instruction.getClass().getName();

        if (instructionType.equals("RInstruction")) {
            RInstruction r = (RInstruction)instruction;

            // AND
            if (r.function == 36) {

            }
            // SLL
            else if (r.function == 0) {

            }
            // JR
            else if (r.function == 8) {

            }
            // ADD AKA MOVE - dont think this is needed
            else if (r.function == 32) {

            }
            // SYSCALL
            else if (r.function == 12) {

            }
            // OR
            else if (r.function == 37) {

            }
        }
        else if (instructionType.equals("IInstruction")) {
            IInstruction i = (IInstruction)instruction;
            // LW
            if (i.opcode == 35) {

            }
            // BEQ
            else if (i.opcode == 8) {

            }
            // ADDI
            else if (i.opcode == 4) {

            }
            // ADDIU
            else if (i.opcode == 5) {

            }
            // BNE
            else if (i.opcode == 5) {

            }
            // ORI
            else if (i.opcode == 13) {

            }
        }
        else if (instructionType.equals("JInstruction")) {
            JInstruction j = (JInstruction) instruction;

            // JAL
            if (j.opcode == 3) {

            }
        }
    }

    /**
     * Displays the registers at the end of the run
     */
    public static void displayRegisters() {

    }

    public int[] readMachineCode(String machine) {
        int[] code = new int[6];
        /*
        for j
        0 opcode, 1 index
        for r
        0 opcode, 1 rs, 2 rt, 3 rd, 4 shamt, 5 func
        for i
        0 opcode, 1 rs, 2 rt, 3 imm
         */
        System.out.println(machine.substring(0,6));
        System.out.println(Integer.parseInt(machine.substring(0,6),2));
        if (machine.substring(0,6).contains("100011") ||
                machine.substring(0,6).contains("001101") ||
                machine.substring(0,6).contains("000100") ||
                machine.substring(0,6).contains("001001") ||
                machine.substring(0,6).contains("000101")) {
            //immediate
            code[0] = Integer.parseInt(machine.substring(0,6), 2); //opcode
            code[1] = Integer.parseInt(machine.substring(6, 11), 2); //rs
            code[2] = Integer.parseInt(machine.substring(11,16), 2); //rt
            code[3] = Integer.parseInt(machine.substring(17,32), 2); //imm
        } else if(machine.substring(0,6).contains("000000")) {
            if(machine.substring(26,32).contains("100101") ||
                    machine.substring(26,32).contains("100000") ||
                    machine.substring(26,32).contains("100100") ||
                    machine.substring(26,32).contains("000000") ||
                    machine.substring(26,32).contains("101000")) {
                //register
                code[0] = Integer.parseInt(machine.substring(0,6), 2); //opcode
                code[1] = Integer.parseInt(machine.substring(6, 11), 2); //rs
                code[2] = Integer.parseInt(machine.substring(11,16), 2); //rt
                code[3] = Integer.parseInt(machine.substring(17,22), 2); //rd
                code[4] = Integer.parseInt(machine.substring(21,26), 2); //shamt
                code[5] = Integer.parseInt(machine.substring(26,32), 2); //func
            }
        } else if (machine.substring(0,6).contains("000010")) {
            //jump
            code[0] = Integer.parseInt(machine.substring(0,6), 2); //opcode
            code[1] = Integer.parseInt(machine.substring(6,32), 2); //26 bit index
        }

        return code;
    }

}
