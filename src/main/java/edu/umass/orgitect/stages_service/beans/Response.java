package edu.umass.orgitect.stages_service.beans;

public class Response<T> {

    private T result;

    public Response(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }
}
