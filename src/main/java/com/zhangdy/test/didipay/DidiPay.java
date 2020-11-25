package com.zhangdy.test.didipay;

import net.iccbank.openapi.sdk.ApiClient;
import net.iccbank.openapi.sdk.DefaultApiClient;
import org.junit.Before;

public class DidiPay {



//    {
//        "code":200,
//                "data":{
//            "appId":"d4f998ab4baa410786286a15a25c25d6",
//                    "appSecret":"MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAMJbl4Wu4/eUmnHA6eW2HKkjj5R9Ik/dTOKJ83UeXJ0ZMbkK4/O/BBP183U9sZukIAEpBT6h2Sey0mt9DbzNQaYy45Q7COENJShn7BKv5DmRm8q1kMDF8lXmw9L0QSZPGIyT//Xizi7e/NE4kCQqU27MhhlpaphdJvKuWtMk3TAgMBAAECgYBwwj4t3tLJumScdKT606z0SAEfLNnh/3fqX1kVW6dSqw8BgtE7QofbLE0Wq2dkHUsxxtVNZCG0tggcdoPZHZK/1bqyL7dIms7IHaPWvLokKAuNNoiQlLgiL+P93YV5n+3k4ZUWlfW6eEpG2X8p9D6DLUCy5gZ4uLF1PHKEqeO06QJBAPgDiG8gSgcxh9qpgTZKjADQvMMgj1+PIisgNqISrgL3VJE0qV9sJVALKVZbNzCu57BZ5jdHMTJSQwrvJDs2nZ0CQQClM3njFGxjAOr7FmlLSgGTfl3chMQWyvzDNWluieMRu1RfQTM/T5l2arvA17FS2j1617MEfANlXqa9SGhDCXYvAkACjubFXqvkzxn7o2v1x2cSSxTnXlqcPbm4gCiQG2k4Fp0Esmpc1Zy86qCOh3pWQjeZlhPN1ionvrl6T2UsRy/xAkB0ZbCbrugvWZY3E7NcuOgjZAX+Og1vgdZWNGv01nKHK9Zmxym0kgEuzkU7ur3WAq8OvqwDnN1Hr0kMVNghY6lBAkAzhMdU+H1xd+YPIl1nUEXDaXcCKK3q8vF/mIDgGUnn/Kqh7u4soRT80LhDa0YtM7hsKHAL3Wf84ieX2XFXhNxz",
//                    "expireDate":"2020-08-02 20:11:02",
//                    "encryptToken":"bd42b0d753b745b0b66465a8de2b93cd"
//        },
//        "msg":"HTTP_OK",
//                "subCode":"0",
//                "subMsg":"success"
//    }
//    "http://58.33.9.130:41008/"


    protected ApiClient apiClient;

//    @Before
//    public void init(){
//        String endpoint     = "http://58.33.9.130:41008/";
//        String appId        = "d4f998ab4baa410786286a15a25c25d6";
//        String appSecret    = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAMJbl4Wu4/eUmnHA6eW2HKkjj5R9Ik/dTOKJ83UeXJ0ZMbkK4/O/BBP183U9sZukIAEpBT6h2Sey0mt9DbzNQaYy45Q7COENJShn7BKv5DmRm8q1kMDF8lXmw9L0QSZPGIyT//Xizi7e/NE4kCQqU27MhhlpaphdJvKuWtMk3TAgMBAAECgYBwwj4t3tLJumScdKT606z0SAEfLNnh/3fqX1kVW6dSqw8BgtE7QofbLE0Wq2dkHUsxxtVNZCG0tggcdoPZHZK/1bqyL7dIms7IHaPWvLokKAuNNoiQlLgiL+P93YV5n+3k4ZUWlfW6eEpG2X8p9D6DLUCy5gZ4uLF1PHKEqeO06QJBAPgDiG8gSgcxh9qpgTZKjADQvMMgj1+PIisgNqISrgL3VJE0qV9sJVALKVZbNzCu57BZ5jdHMTJSQwrvJDs2nZ0CQQClM3njFGxjAOr7FmlLSgGTfl3chMQWyvzDNWluieMRu1RfQTM/T5l2arvA17FS2j1617MEfANlXqa9SGhDCXYvAkACjubFXqvkzxn7o2v1x2cSSxTnXlqcPbm4gCiQG2k4Fp0Esmpc1Zy86qCOh3pWQjeZlhPN1ionvrl6T2UsRy/xAkB0ZbCbrugvWZY3E7NcuOgjZAX+Og1vgdZWNGv01nKHK9Zmxym0kgEuzkU7ur3WAq8OvqwDnN1Hr0kMVNghY6lBAkAzhMdU+H1xd+YPIl1nUEXDaXcCKK3q8vF/mIDgGUnn/Kqh7u4soRT80LhDa0YtM7hsKHAL3Wf84ieX2XFXhNxz";
//        String token        = "bd42b0d753b745b0b66465a8de2b93cd";
//        apiClient           = new DefaultApiClient(endpoint, appId, appSecret, token);
//    }


    @Before
    public void init(){
        String endpoint     = "http://localhost:41039/";
        String appId        = "eb135de2a61847c699ca164efc4e067c";
        String appSecret    = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJzsTn2qR0hrnqTiF6K0rME6aE1CA7fd3bRlm2KSykP45EC356tdWSCIQiTVbEdlmg/XCSUJwllf5hf9kBmmz4cyQFYo2C5I7OdUE13Z/9KzsrVB53jW6dR4fSWy1yl4nMKG22/qcXAaTd7s/o59AxIxrG6/RaC5wj0AV8kP26NxAgMBAAECgYB2HKc3juL4b4iJMmP342XkSsIW5IlH64bkMVYza6kyYDPtQn7tTOW4xtaCQuGtG5xKL+CCMEZIXxYhGIlNxXCNcV7gF4ZcUi+jqaTcZMkpzUv+/VA9P1Wbk9wGVZ/oRa7oMplgG4eOo+Th8RYDOrGcjf9llUPUjP25QMz/rnJMAQJBAOa1NVvTK+1fPx/9Qs4WGf0CaZbkUeg+w0JaqrF+pfcHSe8OCO9bhwo3YkTTE0zaDSS9k62v7qmpBoBOfG/fMPkCQQCuIFYTplQFTMaL5OrAp0zQ+yFauHKp0QqE+ryDGUBNEV8tF8nZ6hj/i57WNqst5KS7FJ5YXZhmhvQ+vNIWYpw5AkEAi4ad7ZnJ4DYRRDRzuZVuRDC4URRmYNHMXw3cW5ofn0VeNmBDkRbGlawnFz6cVoctGcI5VPqomRzrHJ/a7AGMaQJAKXFMy1R0/uXtA3KyZLPs1nhO0fbAY6Z7KHc80r75zN3rlyHoStMqq/ehED7msv+OLXCU11c/ZHeOPjhWkDlcEQJBAL8eaoSyooOPbjSie1bf1K3nlpYhEfP5dpXLMe+J/a+0iU0oACPcHhk4p+HaOxOuc7oMV7H0IvbLJLi0ztey7m8=";
        String token        = "fe10a24312224b1d81c89213faf7e576";
        apiClient           = new DefaultApiClient(endpoint, appId, appSecret, token);
    }

}
