package com.codesoom.http;

import com.codesoom.exception.MethodNotExistException;

public enum HttpMethod {
    GET, POST, PUT, DELETE;

    public boolean isPost() {
        return this == POST;
    }

    public boolean isGet() {
        return this == GET;
    }

    public boolean isPut() {
        return this == PUT;
    }

    public boolean isDelete() {
        return this == DELETE;
    }

    /**
     *
     * @param method HttpMethod 이름에 해당하는 값이다.
     * @return 주어진 문자열에 해당하는 HttpMethod를 반환한다.
     * @throws MethodNotExistException
     *         HttpMethod에 없는 value가 온 경우이다.
     */
    public static HttpMethod of(String method) {
        try {
            return HttpMethod.valueOf(method);
        } catch (IllegalArgumentException e) {
            throw new MethodNotExistException();
        }
    }
}
