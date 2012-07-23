package net.egork.collections.intervaltree;

/**
 * @author Egor Kulikov (egorku@yandex-team.ru)
 */
public interface IntervalTree<V, D> {
    void init();

    void update(int from, int to, D delta);

    V query(int from, int to);
}
