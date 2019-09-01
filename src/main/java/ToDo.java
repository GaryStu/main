class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        super.type = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
