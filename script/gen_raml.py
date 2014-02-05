#! /usr/bin/python

routes = open("routes1")

lines = routes.readlines()

import re

regexp = "[a-zA-Z0-9/}{]+"

dict_raml = {"rest": []}

for line in lines:
    line = re.sub("\$", "{", line)
    line = re.sub("<[\w\d\-\[\]\+]+>", "}", line)
    found = re.findall(regexp, line)
    if len(found) > 0:
        splat = found[1].split("/")[2:]
        tmp_dict = dict_raml
        for i in splat[1:len(splat)]:
            if i not in tmp_dict:
                tmp_dict[i] = {}
                tmp_dict[i]["rest"] = []
            tmp_dict = tmp_dict[i]
        tmp_dict["rest"].append(found[0].lower())


def create_raml(tmp_dict, tabs, prev):
    tmp_str = prev

    tmp_str += (tabs * "  ") + "displayName: TODO\n"
    tmp_str += (tabs * "  ") + "type: common\n"

    for method in tmp_dict["rest"]:
        if method == "post" or method == "put":
            tmp_str += (tabs * "  ") + method + ":\n"
            tmp_str += ((tabs + 1) * "  ") + "is: [TODO]\n"
            tmp_str += ((tabs + 1) * "  ") + "description: TODO\n"
            tmp_str += ((tabs + 1) * "  ") + "body:\n"
            tmp_str += ((tabs + 2) * "  ") + "application/json:\n"
            tmp_str += ((tabs + 3) * "  ") + "schema: !include schemas/TODO.json\n"
        elif method == "get" or method == "patch":
            tmp_str += (tabs * "  ") + method + ":\n"
            tmp_str += ((tabs + 1) * "  ") + "is: [TODO]\n"
            tmp_str += ((tabs + 1) * "  ") + "description: TODO\n"
            tmp_str += ((tabs + 1) * "  ") + "responses:\n"
            tmp_str += ((tabs + 2) * "  ") + "200:\n"
            tmp_str += ((tabs + 3) * "  ") + "body:\n"
            tmp_str += ((tabs + 4) * "  ") + "schema: !include schemas/TODO.json\n"
        else:
            tmp_str += (tabs * "  ") + method + ":\n"
            tmp_str += ((tabs + 1) * "  ") + "description: TODO\n"


    for k in tmp_dict:
        if k is not "rest":
            tmp_str += (tabs * "  ") + "/" + k + ":\n"
            tmp_str = create_raml(tmp_dict[k], tabs + 1, tmp_str)

    return tmp_str

ret = create_raml(dict_raml, 0, "")


file = open("admin_functions.raml", "w")
file.write(ret)
