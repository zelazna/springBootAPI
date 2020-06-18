package engine.models;


import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Result {
    SUCCESS(true, "Congratulations, you're right!"),
    FAILURE(false, "Wrong answer! Please, try again.");

    public boolean success;
    public String feedback;

    Result(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
