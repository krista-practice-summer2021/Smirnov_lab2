import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// считать числа, выбрать четные, возвести в квадрат, отсортировать, занести в файл
class CollectionNumber {

    private File fileIn;
    private File fileOut;

    private List<Integer> inputNumList;

    public CollectionNumber() {
        inputNumList = new ArrayList<>();
    }

    public static void main(String[] args) {

        CollectionNumber setOfNumbers = new CollectionNumber();

        String pathIn = "src/main/resources/input.txt";
        String pathOut = "src/main/resources/output.txt";

        setOfNumbers.setFileIn(new File(pathIn).getAbsoluteFile());
        setOfNumbers.setFileOut(new File(pathOut).getAbsoluteFile());

        setOfNumbers.readFile();
        
        setOfNumbers.writeSqareInFile();
    }

    public void setFileIn(File fileIn) {
        this.fileIn = fileIn;
    }

    public void setFileOut(File fileOut) {
        this.fileOut = fileOut;
    }

    public List<Integer> sortNumbersByIncrease(List<Integer> lines) {
        List<Integer> sortList = new ArrayList<>(lines);
        sortList.sort(Comparator.comparingInt(Integer::intValue));
        return sortList;
    }

    public void readFile() {
        try (Scanner in = new Scanner(fileIn)) {
            // построчное чтение файла
            while (in.hasNextInt()) {
                int num = in.nextInt();
                inputNumList.add(num);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    public List<Integer> filterChetNumber(List<Integer> lines) {
        System.out.println(lines.stream().filter(line -> (line % 2) == 0).collect(Collectors.toList()));
        return lines.stream().filter(line -> (line % 2) == 0).collect(Collectors.toList());
    }

    public void writeSqareInFile() {
        try (FileWriter writer = new FileWriter(fileOut)) {
            // построковая запись в файл
            // отсортированных по убыванию частот и соответствующих строк
            sortNumbersByIncrease(inputNumList);
                try {
                    List<Integer> chetNumber = filterChetNumber(inputNumList);
                    sortNumbersByIncrease(chetNumber).forEach(sortedLine -> {
                        try {
                            writer.write(sortedLine *  sortedLine + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



