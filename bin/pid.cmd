netstat -a -n -o | findstr 8888

tasklist /svc /FI "pid eq 4984"

taskkill /F /PID 4984
