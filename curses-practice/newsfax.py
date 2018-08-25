## -*- coding: utf-8
## A script that displays a rotating set of news headlines
## in the style of teletext services like Ceefax

import curses, time, locale, feedparser, thread, threading, datetime
from threading import Timer
from math import *

locale.setlocale(locale.LC_ALL, '')

win = curses.initscr()
curses.start_color()
curses.use_default_colors()
MAX_Y, MAX_X = win.getmaxyx()
curses.curs_set(0)
curses.noecho()

BLOCK_CHAR = u'\u2588'.encode('utf-8')

def plot(x=0, y=0, s="", pair=curses.color_pair(0)):
    win.addstr(y, x, s, pair)

def format_string(s, max_len):
    """Splits a string s into an array of strings
    separated by spaces."""
    return

def draw_row(y=0, pos_x=0, dim_x=0, ch='', pair=curses.color_pair(0)):
    """Plots a row of ch of length dim_x
    starting at row y, column pos_x"""
    for x in range(pos_x, pos_x + dim_x):
        if x > MAX_X:
            return
        plot(x, y, ch, pair)

def draw_col(x=0, pos_y=0, dim_y=0, ch='', pair=curses.color_pair(0)):
    """Plots a column of ch of length dim_y
    starting at column x, row pos_y"""
    for y in range(pos_y, pos_y + dim_y):
        if y > MAX_Y:
            return
        plot(x, y, ch, pair)

#because python is being stupid, draw_row/draw_col
#must be called within a try block

curses.init_pair(0, curses.COLOR_WHITE, curses.COLOR_BLACK)
curses.init_pair(1, curses.COLOR_BLUE, curses.COLOR_BLACK)
curses.init_pair(2, curses.COLOR_CYAN, curses.COLOR_BLACK)

#The following methods display various types of telefax screens
#such as news, weather, etc.

def opening_screen():
    win.clear()
    try:
        for y in range(3):
            draw_row(y, 0, MAX_X, BLOCK_CHAR, curses.color_pair(1))
        for y in range(MAX_Y - 3, MAX_Y):
            draw_row(y, 0, MAX_X, BLOCK_CHAR, curses.color_pair(1))
    except:
        pass
    
    mystring1 = "Hold on tight"
    mystring2 = "Retrieving news from"
    mystring3 = "World News"
    offset = -5
    bbc_logo = ['##############################',
                '##    ######    ########    ##',
                '##  ##  ####  ##  ####  ######',
                '##    ######    ######  ######',
                '##  ##  ####  ##  ####  ######',
                '##    ######    ########    ##',
                '##############################']
                
    plot(MAX_X // 2 - len(mystring1) // 2, (MAX_Y // 2) - 8,
         mystring1, curses.color_pair(2))
    plot(MAX_X // 2 - len(mystring2) // 2, MAX_Y // 2 - 7,
         mystring2, curses.color_pair(2))
    for b in bbc_logo:
        plot(MAX_X // 2 - len(b) // 2, MAX_Y // 2 + offset,
             b, curses.color_pair(1))
        offset += 1
    plot(MAX_X // 2 - len(mystring3) // 2, MAX_Y // 2 + offset + 1,
         mystring3, curses.color_pair(2))
    win.refresh()

def quit_screen():
    win.clear()
    try:
        for y in range(3):
            draw_row(y, 0, MAX_X, BLOCK_CHAR, curses.color_pair(1))
        for y in range(MAX_Y - 3, MAX_Y):
            draw_row(y, 0, MAX_X, BLOCK_CHAR, curses.color_pair(1))
    except:
        pass

    mystring1 = "Do you want to quit?"
    mystring2 = "Press 'q' to quit"
    plot(MAX_X // 2 - len(mystring1) // 2, MAX_Y // 2,
         mystring1, curses.color_pair(2))
    plot(MAX_X // 2 - len(mystring2) // 2, (MAX_Y // 2) + 1,
         mystring2, curses.color_pair(2))
    win.refresh()

def read_news(news, source=''):
    """Currently designed for BBC News's RSS formatting"""
    win.clear()
    try:
        for y in range(3):
            draw_row(y, 0, MAX_X, BLOCK_CHAR, curses.color_pair(1))
        for y in range(MAX_Y - 3, MAX_Y):
            draw_row(y, 0, MAX_X, BLOCK_CHAR, curses.color_pair(1))
    except:
        pass
    
    right_now = str(datetime.datetime.now().strftime("%a %d %b %Y, %I:%M %p"))
    plot(MAX_X // 2 - len(right_now) // 2, MAX_Y - 2, right_now, curses.color_pair(0))
    header = ''
    if (source == 'bbc'):
        news_type = news['feed']['title'][11:].encode('ascii','ignore')
        header = news_type.upper() + " NEWS FROM BBC NEWS"
    #for n in xrange(3):
    plot(MAX_X // 2 - len(header) // 2, 4,
         header, curses.color_pair(2))
    plot(0, 6, news['entries'][0]['title'].encode('ascii','ignore').upper(), curses.color_pair(0))
    plot(0, 8, news['entries'][0]['summary_detail']['value'].encode('ascii','ignore'), curses.color_pair(0))
    plot(0, 11, news['entries'][1]['title'].encode('ascii','ignore').upper(), curses.color_pair(0))
    plot(0, 13, news['entries'][1]['summary_detail']['value'].encode('ascii','ignore'), curses.color_pair(0))
    plot(0, 16, news['entries'][2]['title'].encode('ascii','ignore').upper(), curses.color_pair(0))
    plot(0, 18, news['entries'][2]['summary_detail']['value'].encode('ascii','ignore'), curses.color_pair(0))
    plot(0, 21, news['entries'][3]['title'].encode('ascii','ignore').upper(), curses.color_pair(0))
    plot(0, 23, news['entries'][3]['summary_detail']['value'].encode('ascii','ignore'), curses.color_pair(0))
    win.refresh()

opening_screen()

while True:
    try:
        world_news = feedparser.parse("http://feeds.bbci.co.uk/news/world/rss.xml")
        us_news = feedparser.parse("http://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml")
        asia_news = feedparser.parse("http://feeds.bbci.co.uk/news/world/asia/rss.xml")
        euro_news = feedparser.parse("http://feeds.bbci.co.uk/news/world/europe/rss.xml")
        mideast_news = feedparser.parse("http://feeds.bbci.co.uk/news/world/middle_east/rss.xml")
        sci_news = feedparser.parse("http://feeds.bbci.co.uk/news/science_and_environment/rss.xml")
        tech_news = feedparser.parse("http://feeds.bbci.co.uk/news/technology/rss.xml")
        health_news = feedparser.parse("http://feeds.bbci.co.uk/news/health/rss.xml")

        news_items = [world_news, us_news, euro_news, asia_news, mideast_news, sci_news,
                      health_news, tech_news]
        
        for n in news_items:
            read_news(n, 'bbc')
            time.sleep(30)
            
    except (KeyboardInterrupt):
        break

curses.echo()
curses.endwin()
