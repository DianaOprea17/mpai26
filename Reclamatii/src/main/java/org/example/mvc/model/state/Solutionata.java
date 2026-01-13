package org.example.mvc.model.state;

public class Solutionata implements State {
    @Override
    public void changeState() {
        System.out.println("The state is already 'Solutionata'. No further changes possible.");
    }
}
