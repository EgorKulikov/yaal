package net.egork.generated.collections.comparator;

/**
 * @author Egor Kulikov
 */
public interface IntComparator {
    public static final IntComparator DEFAULT = new IntComparator() {
        public int compare(int first, int second) {
            if (first < second) {
                return -1;
            }
            if (first > second) {
                return 1;
            }
            return 0;
        }
    };

	public static final IntComparator REVERSE = new IntComparator() {
		public int compare(int first, int second) {
			if (first < second) {
				return 1;
            }
			if (first > second) {
				return -1;
            }
			return 0;
		}
	};

	public int compare(int first, int second);
}
