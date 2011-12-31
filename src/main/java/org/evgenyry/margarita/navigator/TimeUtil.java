package org.evgenyry.margarita.navigator;

public class TimeUtil {

    public static long parseTimeString(String s) {
	int hour;
	int minute;
	int second;
	int firstColon;
	int secondColon;

	if (s == null)
	    throw new java.lang.IllegalArgumentException();

	firstColon = s.indexOf(':');
	secondColon = s.indexOf(':', firstColon + 1);
	if ((firstColon > 0) & (secondColon > 0) & (secondColon < s.length() - 1)) {
	    hour = Integer.parseInt(s.substring(0, firstColon));
	    minute = Integer.parseInt(s.substring(firstColon + 1, secondColon));
	    second = Integer.parseInt(s.substring(secondColon + 1));
	} else {
	    throw new java.lang.IllegalArgumentException("Illegal String: " + s);
	}
	return ((hour * 60 + minute) * 60 + second) * 1000;
    }

}