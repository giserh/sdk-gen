import json
import collections

def create_get(filename, newfilename):
    print filename
    file1 = open(filename, "r")

    js = json.loads(file1.read())

    file1.close()

    if "id" in js:
        del js["id"]

    od = collections.OrderedDict([("id", 1)] + sorted(js.items()))

    if "updatedAt" in od:
        del od["updatedAt"]

    od["updatedAt"] = 1390236382695

    if "createdAt" in od:
        del od["createdAt"]

    od["createdAt"] = 1390236382695
    ret = json.dumps(od, indent=4, separators=(',', ': '))
    file1 = open(newfilename, "w")
    file1.write(ret)


def create_post(filename, newfilename):
    file1 = open(filename, "r")

    js = json.loads(file1.read())

    file1.close()

    if "id" in js:
        del js["id"]
    if "updatedAt" in js:
        del js["updatedAt"]
    if "createdAt" in js:
        del js["createdAt"]

    od = collections.OrderedDict(sorted(js.items()))

    ret = json.dumps(od, indent=4, separators=(',', ': '))
    name = filename.split(".")
    file1 = open(newfilename, "w")  # name[0] + "_post." + name[1], "w")
    file1.write(ret)


def create_list(filename, newfilename):
    file1 = open(filename, "r")

    js = json.loads(file1.read())

    file1.close()

    if "id" in js:
        del js["id"]

    od = collections.OrderedDict([("id", 1)] + sorted(js.items()))

    if "updatedAt" in od:
        del od["updatedAt"]

    od["updatedAt"] = 1390236382695

    if "createdAt" in od:
        del od["createdAt"]

    od["createdAt"] = 1390236382695

    ret = json.dumps([od], indent=4, separators=(',', ': '))
    file1 = open(newfilename, "w")
    file1.write(ret)


import os

for dirname, dirnames, filenames in os.walk('./examples'):

    for filename in filenames:
        whole_path = os.path.join(dirname, filename)
        create_get(whole_path, whole_path.replace("examples", "new"))
        create_list(whole_path, whole_path.replace("examples", "new").replace("_example", "_list_example"))
        if "cache" not in whole_path and "queue" not in whole_path :
            create_post(whole_path, whole_path.replace("examples", "new").replace("_example", "_post_example"))
