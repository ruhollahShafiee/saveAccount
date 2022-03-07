package com.kadmos.service.saveAccount.restController.actuator;

import com.kadmos.service.saveAccount.restController.BalanceController;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.core.log.LogMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class CustomTraceRepository extends InMemoryHttpTraceRepository {

    private static final Logger LOG = LoggerFactory.getLogger("incoming_request_logger");
    @Override
    public List<HttpTrace> findAll() {
        return super.findAll();
    }

    @Override
    public void add(HttpTrace trace) {
        super.add(trace);
        //write logs
        final val request = trace.getRequest();
        final val response = trace.getResponse();
        LOG.info("'request_header':'{}','request_method':'{}','request_URI':'{}','response_header':'{}','response_status':'{}','timestamp':'{}','time_taken(ms)':{}", request.getHeaders(), request.getMethod(), request.getUri(),
                response.getHeaders(),response.getStatus(),trace.getTimestamp(),trace.getTimeTaken());
    }
}
