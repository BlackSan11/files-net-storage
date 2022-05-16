package ru.gb.file.net.storage.streamapi;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<User> userList = Arrays.asList(
                new User("aa1", List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(12))),   //13
                new User("aa1", List.of(BigDecimal.valueOf(10), BigDecimal.valueOf(210))), //220
                new User("cc2", List.of(BigDecimal.valueOf(1), BigDecimal.valueOf(21))),   //22
                new User("cc1", List.of(BigDecimal.valueOf(7))),                           //7
                new User("aa2", Collections.emptyList()),                                  //0
                new User("bb1", Collections.emptyList()));                                 //0
        //
        //Optional<BigDecimal> totalOrdersAmount = userList.stream()
        //        .peek(user -> user.setUserName(user.getUserName() + "_mapped"))
        //        .map(User::getOrders)
        //        .flatMap(List::stream)
        //        .reduce(BigDecimal::add);
        //System.out.println(totalOrdersAmount.get());
        //
        Map<String, List<BigDecimal>> userMap = userList.stream()
                .collect(Collectors.toMap(User::getUserName, User::getOrders));

        List<BigDecimal> totalOrders = userList.stream()
                .map(User::getOrders)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        Path ideaProjectPath = Path.of("/Users/bchervoniy/IdeaProjects/files-net-storage");

        Files.walk(ideaProjectPath, 30)
                .map(Path::toFile)
                .filter(file -> file.isFile())
                .filter(file -> file.getName().endsWith("class"))
                .count();

        boolean ordersMatch = userList.stream()
                .anyMatch(user -> user.getOrders().size() > 0);

        // IntStream.range(1, 10)
        //         .forEach(System.out::println);

        long startTimeMillis = System.currentTimeMillis();
        Optional<BigDecimal> totalOrdersAmount = userList.parallelStream()
                .peek(user -> user.setUserName(user.getUserName() + "_mapped"))
                .map(User::getOrders)
                .flatMap(List::stream)
                .reduce(BigDecimal::add);
        System.out.println(System.currentTimeMillis() - startTimeMillis);
    }
}
