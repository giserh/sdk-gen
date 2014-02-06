from descriptions import desc
import json

def add(filename, newfilename):

    file1 = open(filename, "r")

    js = json.loads(file1.read())

    # print js
    file1.close()
    if "internal" in js["properties"]:
        print filename

    for k in js["properties"]:
        js["properties"][k]["description"] = desc[k]

    ret = json.dumps(js, indent=4, separators=(',', ': '))
    file1 = open(newfilename, "w")
    print filename
    file1.write(ret)

import os

for dirname, dirnames, filenames in os.walk('./schemas'):
    for filename in filenames:
        whole_path = os.path.join(dirname, filename)
        add(whole_path, whole_path)
