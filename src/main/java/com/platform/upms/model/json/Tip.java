package com.platform.upms.model.json;

/**
 * json返回用的实体类
 */
public class Tip {

    /**
     * 状态：
     * true or false
     */
	Boolean success;

    /**
     * 处理状态标识
     * -1 服务器拒绝访问/未登录
     * 0 暂未定义
     * 1 错误1
     * 2 错误2
     * ...
     */
    Integer code;


    String msg;

    /**
     * 需要返回的数据，可以是任何对象
     */
    Object data;

    /**
     * 默认为 true
     */
    public Tip(){
        this.success = true;
    }

    /**
     * 操作成功，并携带一段数据
     */
    public Tip(Object data){
        this.success = true;
        this.data = data;
    }

    /**
     * 操作成功，并携带一条提示
     */
    public Tip(String msg){
        this.success = true;
        this.msg = msg;
    }

    /**
     * 返回错误，并标识错误代码
     * @param code
     */
    public Tip(Integer code){
        this.success = false;
        this.code = code;
    }

    public Tip(Integer code, String msg){
        this.success = false;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 默认成功 返回一条提示和数据
     * @param msg
     * @param data
     */
    public Tip(String msg, Object data){
        this.success = true;
        this.msg = msg;
        this.data = data;
    }



    /**
     * 返回指定标识错误，并携带一段提示和数据；
     * @param code
     * @param msg
     * @param data
     */
    public Tip(Integer code, String msg, Object data){
        this.success = false;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回指定标识错误，并携带一段数据；
     * @param code
     * @param data
     */
    public Tip(Integer code, Object data){
        this.success = false;
        this.code = code;
        this.data = data;
    }




    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Tip{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
