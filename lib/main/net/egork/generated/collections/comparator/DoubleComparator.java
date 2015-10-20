package net.egork.generated.collections.comparator;

/**
 * @author Egor Kulikov
 */
public interface DoubleComparator {
    public static final DoubleComparator DEFAULT = new DoubleComparator() {
        public int compare(double first, double second) {
            if (first < second) {
                return -1;
            }
            if (first > second) {
                return 1;
            }
            return 0;
        }
    };

	public static final DoubleComparator REVERSE = new DoubleComparator() {
		public int compare(double first, double second) {
			if (first < second) {
				return 1;
            }
			if (first > second) {
				return -1;
            }
			return 0;
		}
	};

	public int compare(double first, double second);
}
