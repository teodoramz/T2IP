public interface Human extends Comparable<Human> {
    void greeting();

    void doWork();

    @Override
    int compareTo(Human o);
    int getVarsta();
}
