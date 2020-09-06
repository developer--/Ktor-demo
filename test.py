import cfscrape
from selenium import webdriver
from selenium.webdriver.firefox.firefox_binary import FirefoxBinary

# scraper = cfscrape.create_scraper()
# print scraper.get("http://copart.com").content  # => "<!DOCTYPE html><html><head>..."


def scrape_page(url):
    browser = webdriver.Chrome()
    browser.get(url)
    content = browser.page_source
    browser.close()
    return content


if __name__ == "__main__":
    print(scrape_page('https://copart.com/'))