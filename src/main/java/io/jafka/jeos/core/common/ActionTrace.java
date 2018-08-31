package io.jafka.jeos.core.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//TODO
public class ActionTrace {

    private Action act;

    private String console;

    private Integer cpuUsage;

    private Integer elapsed;

    private List<Object> inlineTraces = null;

    private Receipt receipt;

    private Integer totalCpuUsage;

    private String trxId;

    public Action getAct() {
        return act;
    }

    @JsonProperty("act")
    public void setAct(Action act) {
        this.act = act;
    }

    public String getConsole() {
        return console;
    }

    @JsonProperty("console")
    public void setConsole(String console) {
        this.console = console;
    }

    public Integer getCpuUsage() {
        return cpuUsage;
    }

    @JsonProperty("cpu_usage")
    public void setCpuUsage(Integer cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Integer getElapsed() {
        return elapsed;
    }

    @JsonProperty("elapsed")
    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    public List<Object> getInlineTraces() {
        return inlineTraces;
    }

    @JsonProperty("inline_traces")
    public void setInlineTraces(List<Object> inlineTraces) {
        this.inlineTraces = inlineTraces;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    @JsonProperty("receipt")
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Integer getTotalCpuUsage() {
        return totalCpuUsage;
    }

    @JsonProperty("total_cpu_usage")
    public void setTotalCpuUsage(Integer totalCpuUsage) {
        this.totalCpuUsage = totalCpuUsage;
    }

    public String getTrxId() {
        return trxId;
    }

    @JsonProperty("trx_id")
    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

}
