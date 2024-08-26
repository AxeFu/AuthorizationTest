package common;

public class WordBuilder {
    public static String getRandomWord(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length-2; i++) {
            if (i <= length/2 - 1) {
                stringBuilder.append(randomLetter());
            } else {
                stringBuilder.append(randomNumber());
            }
        }
        stringBuilder.append(randomSpecial());
        stringBuilder.append(randomUpperCaseLetter());
        return stringBuilder.toString();
    }

    public static char randomNumber() {
        return randomSymbol('0', '9');
    }

    public static char randomLetter() {
        return randomSymbol('a', 'z');
    }

    public static char randomUnicode() {
        return randomSymbol('~'+1, Character.MAX_VALUE);
    }

    public static char randomUpperCaseLetter() {
        return randomSymbol('A', 'Z');
    }

    public static char randomSpecial() {
        int index = (int)(Math.random()*4);
        return switch (index) {
            case 1 -> randomSymbol('!', '/');
            case 2 -> randomSymbol(':', '@');
            case 3 -> randomSymbol('[', '`');
            default -> randomSymbol('{', '~');
        };
    }

    public static char randomSymbol(int start, int end) {
        return (char)(Math.random()*(end - start) + start);
    }
}
