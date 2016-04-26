#!/usr/bin/env python

## generates mock click logs data
## log format
##   timestamp (in ms), ip_address, user, page

## timestamp converstions testing site : http://www.epochconverter.com/


## ----- config
days=4
entries_per_day=10000
total_users = 100
total_ips = 100
total_urls = 50

#log_format="csv"
log_format="json"
## --- end config


import os
import datetime as dt
import random
import json



# overwrite this function to customize log generation
def generate_log(timestamp):
  user = "user_%d" % random.randint(1,total_users)
  ip_address = "ip_%d" % random.randint(1,total_ips)
  url = "url_%d" % random.randint(1,total_urls)

  #csv
  if (log_format == 'csv'):
    logline = "%s,%s,%s,%s" % (timestamp, ip_address, user, url)

  # generate JSON format
  if (log_format == 'json'):
    dict={'timestamp': timestamp, 'ip': ip_address, 'user': user, 'url': url}
    logline = json.dumps(dict)


  #print logline
  return logline



#main
## --- script main
if __name__ == '__main__':

  # mkdir
  if not os.path.exists(log_format):
    os.mkdir(log_format)

  time_inc_ms = int ((24.0*3600*1000)/entries_per_day)
  #print "time inc ms", time_inc_ms
  #epoch = dt.datetime.fromtimestamp(0)
  epoch = dt.datetime(1970,1,1)

  year_start = dt.datetime(2015, 1, 1)
  for day in range(0, days):
    day_delta = dt.timedelta(days=day)
    start_ts = year_start + day_delta
    #end_ts = dt.datetime(start_ts.year, start_ts.month, start_ts.day, 23, 59, 59)
    end_ts = dt.datetime(start_ts.year, start_ts.month, start_ts.day+1, 0, 0, 0)
    filename = start_ts.strftime("%Y-%m-%d") 
    if (log_format == 'csv'):
      filename = filename + ".csv"
    if (log_format == 'json'):
      filename = filename + ".json"

    filename = log_format + "/" + filename

    #print start_ts
    #print end_ts
    last_ts = start_ts

    with open(filename, "w") as fout:
      print "generating log ", filename
      while (last_ts < end_ts):
        delta_since_epoch = last_ts - epoch
        millis = int((delta_since_epoch.microseconds + (delta_since_epoch.seconds + delta_since_epoch.days * 24 * 3600) * 10**6) / 1e3)
        #print "last ts", last_ts
        #print "millis",  millis
        logline = generate_log(millis)
        fout.write(logline + "\n")

        last_ts = last_ts + dt.timedelta(milliseconds=time_inc_ms)

