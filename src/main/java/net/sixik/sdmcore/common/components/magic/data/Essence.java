package net.sixik.sdmcore.common.components.magic.data;

public record Essence(String name, float value) {
    @Override
    public String name() {
        return name;
    }
}
