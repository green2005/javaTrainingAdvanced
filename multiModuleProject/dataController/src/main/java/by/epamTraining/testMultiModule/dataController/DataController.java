package by.epamTraining.testMultiModule.dataController;

import by.epamTraining.testMultiModule.repository.Repository;
import by.epamTraining.testMultiModule.repository.User;

import java.util.List;
import java.util.stream.Collectors;

public class DataController {
    private Repository repository;
      //
    DataController() {
        repository = new Repository();
    }

    public List<User> getUsers() {
        //get only adults
        //should check in sql, its just an example

        return repository.getUsers().stream().filter(user ->
                user.getAge() > 18
        ).collect(Collectors.toList());
    }
}
