package org.example.utils;

import org.example.vo.MyResult;

public class ResultUtil {

    public static MyResult success(Object object) {
        MyResult resultVO = new MyResult();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }

    public static MyResult success() {
        return success(null);
    }

    public static MyResult error(Integer code, String msg) {
        MyResult resultVO = new MyResult();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
