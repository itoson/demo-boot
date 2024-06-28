package org.example.utils;

import org.example.vo.Result;

public class ResultUtil {

    public static Result success(Object object) {
        Result resultVO = new Result();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result resultVO = new Result();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
