package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	String list = "1. George Washington \n" +
		"2. John Adams \n" +
		"3. Thomas Jefferson \n" +
		"4. James Madison \n" +
		"5. James Monroe \n" +
		"6. John Adams \n" +
		"7. Andrew Jackson \n" +
		"8. Martin Van_Buren \n" +
		"9. William Harrison \n" +
		"10. John Tyler \n" +
		"11. James Polk \n" +
		"12. Zachary Taylor \n" +
		"13. Millard Fillmore \n" +
		"14. Franklin Pierce \n" +
		"15. James Buchanan \n" +
		"16. Abraham Lincoln \n" +
		"17. Andrew Johnson \n" +
		"18. Ulysses S. Grant \n" +
		"19. Rutherford B. Hayes \n" +
		"20. James Garfield \n" +
		"21. Chester Arthur \n" +
		"22. Grover Cleveland \n" +
		"23. Benjamin Harrison \n" +
		"24. Grover Cleveland \n" +
		"25. William McKinley \n" +
		"26. Theodore Roosevelt \n" +
		"27. William Taft \n" +
		"28. Woodrow Wilson \n" +
		"29. Warren Harding \n" +
		"30. Calvin Coolidge \n" +
		"31. Herbert Hoover \n" +
		"32. Franklin D. Roosevelt \n" +
		"33. Harry Truman \n" +
		"34. Dwight D. Eisenhower \n" +
		"35. John F. Kennedy \n" +
		"36. Lyndon Johnson \n" +
		"37. Richard Nixon \n" +
		"38. Gerald Ford \n" +
		"39. James Carter \n" +
		"40. Ronald Reagan ";

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String[] tokens = list.split(" ");
		int index = in.readInt();
		int i = 0;
		while (true) {
			i += 2;
			if (tokens[i].length() == 2)
				i++;
			index--;
			if (index == 0) {
				out.printLine(tokens[i].replace('_', ' '));
				return;
			}
			i++;
		}
    }
}
