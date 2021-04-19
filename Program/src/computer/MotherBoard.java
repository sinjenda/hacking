package computer;

public class MotherBoard {
    Cpu cpu;
    Ram ram;

    public MotherBoard(Cpu cpu, Ram ram, Disk disk) {
        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
    }

    Disk disk;
}
