package com.audition.configuration;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class ResponseHeaderInjector implements HandlerInterceptor {

    //  Inject openTelemetry trace and span Ids in the response headers.

    private final Tracer tracer;

    public ResponseHeaderInjector() {
        // Get a Tracer instance from the Global OpenTelemetry registry
        this.tracer = GlobalOpenTelemetry.getTracer("com.audition");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        Span currentSpan = Span.current();
        if (currentSpan != null && currentSpan.getSpanContext().isValid()) {
            response.addHeader("Trace-ID", currentSpan.getSpanContext().getTraceId());
            response.addHeader("Span-ID", currentSpan.getSpanContext().getSpanId());
        }
    }


}
