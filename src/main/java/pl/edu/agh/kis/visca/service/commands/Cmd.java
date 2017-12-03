package pl.edu.agh.kis.visca.service.commands;

public abstract class Cmd {
    public Cmd() {
    }

    public abstract byte[] createCommandData();
}
