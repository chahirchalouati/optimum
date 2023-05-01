export namespace ObjectsUtils {
  export function isNotEmpty(obj: any) {
    return !ObjectsUtils.isEmpty(obj);
  }

  export function isEmpty(obj: any) {
    if (obj === null || obj === undefined)
      return true;
    if (obj instanceof Array) {
      return !!obj && obj.length > 0
    }
    if (obj instanceof String) {
      return !!obj && obj.length > 0;
    }
    return false;
  }


}

export namespace StringUtils{
  export function isBlank(obj: any) {
    if (obj === null || obj === undefined)
      return true;
    if (obj instanceof String) {
      return !!obj && obj.length > 0 && obj.trim().length > 0;
    }
    return false;
  }
}
