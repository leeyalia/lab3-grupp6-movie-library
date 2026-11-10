package se.iths.grupp6.app.model;

public class ActionMovie extends Movie{

    public ActionMovie() {}

    public ActionMovie(int id, String title) {
        super(id, title);
    }

    @Override
    public String category() {
        return "Action";
    }

}
