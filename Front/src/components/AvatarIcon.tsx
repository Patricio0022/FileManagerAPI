import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"


export function AvatarIcon(){
    return(
        <Avatar className="w-50 h-[300px] transition-transform duration-300 ease-in-out transform hover:scale-110 "> 
        <AvatarImage src="https://github.com/shadcn.png" />
        <AvatarFallback>CN</AvatarFallback>
      </Avatar>
      
    );

}