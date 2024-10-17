#!/bin/bash

ps -ef|grep dca-store |grep -v grep| awk '{print $2}'|xargs kill -9