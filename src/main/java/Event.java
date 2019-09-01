//tasks that start at a specific time and ends at a specific time
class Event extends Task {
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
        super.type = "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }
}