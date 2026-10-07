# WholeCart Marketplace – Load Test Results

## 1. Test Execution

**Application:** WholeCart Marketplace
**Tool:** Apache JMeter 5.6.3
**Base URL:** `http://13.200.185.1/c/deepika-51b4ee`

| Parameter                  |              Value |
| -------------------------- | -----------------: |
| Concurrent Users           |                 10 |
| Ramp-up Period             |         10 seconds |
| Loop Count                 |                  6 |
| Total Samples              |                180 |
| Maximum Allowed Throughput | 50 requests/second |

## 2. APIs Under Test

### Login API

```text
POST /c/deepika-51b4ee/api/login
```

### Product API

```text
GET /c/deepika-51b4ee/api/products/513
```

## 3. JMeter Load Test Results

| Metric                     | Result |
| -------------------------- | -----: |
| Total Requests             |    180 |
| Average Response Time (ms) |      0 |
| Minimum Response Time (ms) |      0 |
| Maximum Response Time (ms) |      0 |
| Standard Deviation (ms)    |      0 |
| Error %                    |   1.0% |
| Throughput (requests/sec)  |   0.43 |
| Received KB/sec            |   0.34 |
| Sent KB/sec                |   0.00 |
| Average Bytes Received     |    809 |

## 4. Server Metrics

**Endpoint:**

```text
GET /c/deepika-51b4ee/api/metrics
```

**Observed response:**

```json
{
  "window_seconds": 60,
  "requests": 0,
  "errors_5xx": 0,
  "p50_ms": null,
  "p95_ms": null,
  "orders_in_flight": 0,
  "version": "1.0"
}
```

| Server Metric       |     Result |
| ------------------- | ---------: |
| Monitoring Window   | 60 seconds |
| Requests            |          0 |
| 5xx Errors          |          0 |
| P50 Response Time   |        N/A |
| P95 Response Time   |        N/A |
| Orders In Flight    |          0 |
| Application Version |        1.0 |

## 5. Observations

* A total of 180 API samples were executed using Apache JMeter.
* The observed throughput was 0.43 requests/second.
* The observed throughput remained significantly below the maximum allowed limit of 50 requests/second.
* JMeter reported an error percentage of 1.0%.
* The `/api/metrics` endpoint reported 0 requests within its 60-second monitoring window.
* The `/api/metrics` endpoint reported 0 HTTP 5xx errors.
* Server-side P50 and P95 response-time metrics were not available.
* No orders were in flight when the server metrics were checked.
* The application version reported by the metrics endpoint was 1.0.

## 6. Test Status

**Load Test Execution: COMPLETED**

**Throughput Limit: PASS**

**Server 5xx Errors: PASS**

**JMeter Error Rate: 1.0% — Requires Observation**
