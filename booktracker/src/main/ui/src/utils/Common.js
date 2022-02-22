export const getUser = () => {
    const userStr = sessionStorage.getItem('firstName')
    if (userStr) return JSON.parse(userStr)
    else return null
}